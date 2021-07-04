package com.tsoftmobile.t_softar;

import com.google.ar.sceneform.ux.BaseTransformableNode;
import com.google.ar.sceneform.ux.RotationController;
import com.google.ar.sceneform.ux.TransformationSystem;
import com.google.ar.sceneform.ux.TranslationController;

public class ux {

    public static class TransformableNode2 extends BaseTransformableNode {
        private final TranslationController translationController;
//        private final ScaleController scaleController;
        private final RotationController rotationController;

        @SuppressWarnings("initialization") // Suppress @UnderInitialization warning.
        public TransformableNode2(TransformationSystem transformationSystem) {
            super(transformationSystem);

            translationController =
                    new TranslationController(this, transformationSystem.getDragRecognizer());
            addTransformationController(translationController);

//            scaleController = new ScaleController(this, transformationSystem.getPinchRecognizer());
//            addTransformationController(scaleController);

            rotationController = new RotationController(this, transformationSystem.getTwistRecognizer());
            addTransformationController(rotationController);
        }

        /** Returns the controller that translates this node using a drag gesture. */
        public TranslationController getTranslationController() {
            return translationController;
        }

//        /** Returns the controller that scales this node using a pinch gesture. */
//        public ScaleController getScaleController() {
//            return scaleController;
//        }

        /** Returns the controller that rotates this node using a twist gesture. */
        public RotationController getRotationController() {
            return rotationController;
        }
    }
}
