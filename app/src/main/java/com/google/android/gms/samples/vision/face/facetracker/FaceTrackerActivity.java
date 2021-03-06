
package com.google.android.gms.samples.vision.face.facetracker;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.samples.vision.face.facetracker.ui.camera.SensorActivity;
import com.google.android.gms.samples.vision.face.facetracker.Activities.BeforeEyeExaminationActivity;
import com.google.android.gms.samples.vision.face.facetracker.Activities.BeforeRemoveGlassActivity;
import com.google.android.gms.samples.vision.face.facetracker.Activities.FinalTouchImageViewActivity;
import com.google.android.gms.samples.vision.face.facetracker.Activities.FrequencyActivity;
import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.Functions.Functions;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.samples.vision.face.facetracker.ui.camera.CameraSourcePreview;
import com.google.android.gms.samples.vision.face.facetracker.ui.camera.GraphicOverlay;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.android.gms.samples.vision.face.facetracker.Extras.Globals.LEFT_FLOAT;
import static com.google.android.gms.samples.vision.face.facetracker.Extras.Globals.LEFT_INT;
import static com.google.android.gms.samples.vision.face.facetracker.Extras.Globals.RIGHT_FLOAT;
import static com.google.android.gms.samples.vision.face.facetracker.Extras.Globals.RIGHT_INT;
import static com.google.android.gms.samples.vision.face.facetracker.Extras.Globals.SPH;
import static java.lang.Thread.sleep;

/**
 * Activity for the face tracker app.  This app detects faces with the rear facing camera, and draws
 * overlay graphics to indicate the position, size, and ID of each face.
 */
public final class FaceTrackerActivity extends AppCompatActivity {
    private static final String TAG = "FaceTracker";

    private CameraSource mCameraSource = null;

    private CameraSourcePreview mPreview;
    private GraphicOverlay mGraphicOverlay;
    public Timer t;
    Button btn;
    // public SensorActivity s;

    private static final int RC_HANDLE_GMS = 9001;
    // permission request codes need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    //==============================================================================================
    // Activity Methods
    //==============================================================================================

    /**
     * Initializes the UI and initiates the creation of a face detector.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.faceOverlay);
        btn = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Toast.makeText(FaceTrackerActivity.this,String.valueOf(  Globals.cntFacesApearance), Toast.LENGTH_SHORT).show();
                if(Globals.cntFacesApearance==1){
                    Globals.cntFacesApearance=0;
                    if((Globals.scale_image = CreateScaleImage()) < 1)
                        Globals.scale_image = 1;
                    Log.d("debug", "Screen Inchesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss:"+  Globals.scale_image);
                   // Toast.makeText(this,String.valueOf(   Globals.scale_image), Toast.LENGTH_SHORT).show();
                    try{
                    t.cancel();
                    }
                    catch(Exception e){
                        Log.e("",e.getMessage());
                    }


                    t = new Timer();
                    //Set the schedule function and rate, MINIMUM 5 SECONDS FOR MANUAL FREQUENCY
                    if ((Globals.frequency > 4) && (Globals.APP_MODE==1)) {
                        t.schedule(new TimerTask() {
                                                  @Override
                                                  public void run() {
                                                        Intent intent = new Intent(FaceTrackerActivity.this, FaceTrackerActivity.class);
                                                        startActivity(intent);
                                                  }
                                              },
                    //Set how long before to start calling the TimerTask (in milliseconds)
                                Globals.frequency * 1000);
                    }
                    if(Globals.APP_MODE == 1) {
                        Intent intent = new Intent(FaceTrackerActivity.this, FinalTouchImageViewActivity.class);
                        startActivity(intent);
                    }
                    else{//Globals.APP_MODE == 0
                        Intent intent = new Intent(FaceTrackerActivity.this, BeforeRemoveGlassActivity.class);
                        startActivity(intent);
                    }

                }
                else if(Globals.cntFacesApearance==0){
                    Toast.makeText(FaceTrackerActivity.this,"Your face was not recognized :(", Toast.LENGTH_SHORT).show();
                }
                else if(Globals.cntFacesApearance>1){
                    Toast.makeText(FaceTrackerActivity.this,"There are more than one recognize face :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }
    }

    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the barcode detector to detect small barcodes
     * at long distances.
     */
    private void createCameraSource() {

        Context context = getApplicationContext();
        FaceDetector detector = new FaceDetector.Builder(context)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();
//need to remove - detect lot of faces//
        detector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory())
                        .build());

        if (!detector.isOperational()) {
            // Note: The first time that an app using face API is installed on a device, GMS will
            // download a native library to the device in order to do detection.  Usually this
            // completes before the app is run for the first time.  But if that download has not yet
            // completed, then the above call will not detect any faces.
            //
            // isOperational() can be used to check if the required native library is currently
            // available.  The detector will automatically become operational once the library
            // download completes on device.
            Log.w(TAG, "Face detector dependencies are not yet available.");
        }

        mCameraSource = new CameraSource.Builder(context, detector)
                .setRequestedPreviewSize(640, 480)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(30.0f)
                .build();
    }

    /**
     * Handles the requesting of the camera permission.  This includes
     * showing a "Snackbar" message of why the permission is needed then
     * sending the request.
     */
    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }

        final Activity thisActivity = this;

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions,
                        RC_HANDLE_CAMERA_PERM);
            }
        };

        Snackbar.make(mGraphicOverlay, R.string.permission_camera_rationale,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, listener)
                .show();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();

        startCameraSource();

    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();

        mPreview.stop();
    }

    /**
     * Releases the resources associated with the camera source, the associated detector, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }

        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Face Tracker sample")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(R.string.ok, listener)
                .show();
    }

    //==============================================================================================
    // Camera Source Preview
    //==============================================================================================

    /**
     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private void startCameraSource() {

        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }

    //==============================================================================================
    // Graphic Face Tracker
    //==============================================================================================

    /**
     * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
     * uses this factory to create face trackers as needed -- one for each individual.
     */
    private class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
        @Override
        public Tracker<Face> create(Face face) {
            return new GraphicFaceTracker(mGraphicOverlay);
        }
    }

    /**
     * Face tracker for each detected individual. This maintains a face graphic within the app's
     * associated face overlay.
     */
    private class GraphicFaceTracker extends Tracker<Face> {
        private GraphicOverlay mOverlay;
        private FaceGraphic mFaceGraphic;
        Context c = getApplicationContext();

        GraphicFaceTracker(GraphicOverlay overlay) {
            mOverlay = overlay;
          /*  try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //   Toast.makeText(c.getApplicationContext(),""+ s.getDistance2(),Toast.LENGTH_LONG).show();
            //  System.out.println( "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + s.getDistance2());
            mFaceGraphic = new FaceGraphic(overlay, c);
        }

        /**
         * Start tracking the detected face instance within the face overlay.
         */
        @Override
        public void onNewItem(int faceId, Face item) {
            mFaceGraphic.setId(faceId);
         //   Globals.isFaceApearance = 1;
            Globals.cntFacesApearance =  Globals.cntFacesApearance+1;
        }

        /**
         * Update the position/characteristics of the face within the overlay.
         */
        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
            mOverlay.add(mFaceGraphic);
            mFaceGraphic.updateFace(face);
        }

        /**
         * Hide the graphic when the corresponding face was not detected.  This can happen for
         * intermediate frames temporarily (e.g., if the face was momentarily blocked from
         * view).
         */
        @Override
        public void onMissing(FaceDetector.Detections<Face> detectionResults) {
            mOverlay.remove(mFaceGraphic);
         //   Globals.isFaceApearance = 0;
            Globals.cntFacesApearance =  Globals.cntFacesApearance-0.5;
        }

        /**
         * Called when the face is assumed to be gone for good. Remove the graphic annotation from
         * the overlay.
         */
        @Override
        public void onDone() {
            mOverlay.remove(mFaceGraphic);
        }
    }

    public double CreateScaleImage() {
        double far_point = 0;


        SPH = (Functions.CreateFloatNumber(Globals.eyes[LEFT_INT], Globals.eyes[LEFT_FLOAT]) +
                Functions.CreateFloatNumber(Globals.eyes[RIGHT_INT], Globals.eyes[RIGHT_FLOAT])) / 2;
            far_point = Functions.CalculateFarPoint(SPH);


       return  Functions.OptimalDiagonalSize(Functions.CmToInch(Globals.distance))/(Functions.OptimalDiagonalSize(Functions.CmToInch(far_point)));

    }
    @Override
    public void onBackPressed() {
        if(Globals.APP_MODE == 1) {
            Globals.cntFacesApearance = 0;
            Intent intent = new Intent(FaceTrackerActivity.this, FrequencyActivity.class);
            startActivity(intent);
        }
        else{//Globals.APP_MODE == 0
            Toast.makeText(FaceTrackerActivity.this,"You can't go back until you will complete the test.", Toast.LENGTH_SHORT).show();
        }

    }
}
