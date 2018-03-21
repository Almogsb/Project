/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.samples.vision.face.facetracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.samples.vision.face.facetracker.Extras.Globals;
import com.google.android.gms.samples.vision.face.facetracker.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

import static java.lang.Math.log;

/**
 * Graphic instance for rendering face position, orientation, and landmarks within an associated
 * graphic overlay view.
 */
class FaceGraphic extends GraphicOverlay.Graphic {
    private static final float FACE_POSITION_RADIUS = 10.0f;
    private static final float ID_TEXT_SIZE = 40.0f;
    private static final float ID_Y_OFFSET = 50.0f;
    private static final float ID_X_OFFSET = -50.0f;
    private static final float BOX_STROKE_WIDTH = 5.0f;

    private static final int COLOR_CHOICES[] = {
        Color.BLUE,
        Color.CYAN,
        Color.GREEN,
        Color.MAGENTA,
        Color.RED,
        Color.WHITE,
        Color.YELLOW
    };
    private static int mCurrentColorIndex = 0;

    private Paint mFacePositionPaint;
    private Paint mIdPaint;
    private Paint mBoxPaint;

    private volatile Face mFace;
    private int mFaceId;
    private float mFaceHappiness;
    float fLeftEyeX , fLeftEyeY , fRightEyeX , fRightEyeY , fPowCalculateTemp, fEyesDistance , fFaceToScreenDistance;
    private String m1;
    Context c;
    int flag = 1;

    FaceGraphic(GraphicOverlay overlay, Context c) {
        super(overlay);
        this.c = c;
        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mFacePositionPaint = new Paint();
        mFacePositionPaint.setColor(selectedColor);

        mIdPaint = new Paint();
        mIdPaint.setColor(selectedColor);
        mIdPaint.setTextSize(ID_TEXT_SIZE+30);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(selectedColor);
        mBoxPaint.setStyle(Paint.Style.STROKE);
        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
    }

    void setId(int id) {
        mFaceId = id;
    }


    /**
     * Updates the face instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateFace(Face face) {
        mFace = face;
        postInvalidate();
    }

    /**
     * Draws the face annotations for position on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Face face = mFace;
        if (face == null) {
            return;
        }

        // Draws a circle at the position of the detected face, with the face's track id below.
        float x = translateX(face.getPosition().x + face.getWidth() / 2);
        float y = translateY(face.getPosition().y + face.getHeight() / 2);
        canvas.drawCircle(x, y, FACE_POSITION_RADIUS, mFacePositionPaint);
        canvas.drawText("id: " + mFaceId, x + ID_X_OFFSET, y + ID_Y_OFFSET, mIdPaint);
       // canvas.drawText("happiness: " + String.format("%.2f", face.getIsSmilingProbability()), x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint);
      //  canvas.drawText("right eye: " + String.format("%.2f", face.getIsRightEyeOpenProbability()), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint);
      //  canvas.drawText("left eye: " + String.format("%.2f", face.getIsLeftEyeOpenProbability()), x - ID_X_OFFSET*2, y - ID_Y_OFFSET*2, mIdPaint);
        canvas.drawText("Eyes Ditance: ", x - ID_X_OFFSET*-6, y - ID_Y_OFFSET*9, mIdPaint);
        canvas.drawText(String.format("%.3f",fEyesDistance), x - ID_X_OFFSET*-6, y - ID_Y_OFFSET*7, mIdPaint);
        canvas.drawText("Face-To-Screen Distance: ", x - ID_X_OFFSET*-6, y - ID_Y_OFFSET*-9, mIdPaint);
        canvas.drawText(String.format("%.3f",fFaceToScreenDistance), x - ID_X_OFFSET*-6, y - ID_Y_OFFSET*-11, mIdPaint);
        Log.d("BLA", "run: " + fFaceToScreenDistance);
        Log.d("fLeftEyeX", "run: " + fLeftEyeX);
        Log.d("fLeftEyeY", "run: " + fLeftEyeY);
        Log.d("fRightEyeX", "run: " + fRightEyeX);
        Log.d("fRightEyeY", "run: " + fRightEyeY);
        Log.d("fPowCalculateTemp", "run: " + fPowCalculateTemp);
        Log.d("fEyesDistance", "run: " + fEyesDistance);
        face.getEulerY();
        CalculateDistance(face);


        // Draws a bounding box around the face.
        float xOffset = scaleX(face.getWidth() / 2.0f);
        float yOffset = scaleY(face.getHeight() / 2.0f);
        float left = x - xOffset;
        float top = y - yOffset;
        float right = x + xOffset;
        float bottom = y + yOffset;
        canvas.drawRect(left, top, right, bottom, mBoxPaint);
    }

    public void CalculateDistance(Face face)
    {
        for (Landmark landmark : face.getLandmarks()) {
            switch (landmark.getType()) {
                case Landmark.LEFT_EYE:
                    fLeftEyeX = landmark.getPosition().x;
                    fLeftEyeY = landmark.getPosition().y;

                    // Toast.makeText(c.getApplicationContext(), "x: " + m1 + "y: " + m2,Toast.LENGTH_LONG).show();
                    break;
                case Landmark.RIGHT_EYE:
                    // m3 = String.format("%.2f", landmark.getPosition().x);
                    fRightEyeX = landmark.getPosition().x;
                    fRightEyeY = landmark.getPosition().y;

                    //    Toast.makeText(c.getApplicationContext(), "x: " + m3 + "y: " + m4,Toast.LENGTH_LONG).show();
                    break;
                default:
                    if(fLeftEyeX!=0 && fLeftEyeY!=0 && fRightEyeX != 0 && fRightEyeY!=0) {
                        fPowCalculateTemp = (float) Math.pow(fLeftEyeX - fRightEyeX, 2) + (float) Math.pow(fLeftEyeY - fRightEyeY, 2);
                        fEyesDistance = (float)( Math.sqrt(fPowCalculateTemp)*(640/480));
                        // y = -6.1x + 279.6
                        fFaceToScreenDistance = (float) ((-Math.log(fEyesDistance/346.92)) /  0.043);
                        Globals.distance = (float) ((-Math.log(fEyesDistance/346.92)) /  0.043);
                        //  fFaceToScreenDistance = (float)((fEyesDistance - 279.6) /(-6.1));
                        //  Math.(100,2);

                    }
                    break;

                 /*   case Landmark.RIGHT_EYE:
                        m2 = String.format("%.2f", landmark.getPosition());
                        // canvas.drawText("left eyed1: " + String.format("%.2f", landmark.getPosition()), x - ID_X_OFFSET * 2, y - ID_Y_OFFSET * 2, mIdPaint);
*/

            }

        }
    }


}
