package entity.enumVariables;

import constants.QuestionConstants;

public enum QuestionDifficulty {SIMPLE(QuestionConstants.QUESTION_DIFFICULTY_SIMPLE), HARD(QuestionConstants.QUESTION_DIFFICULTY_HARD);

        public String getDifficult() {
            return Difficult;
        }

        public void setTip(String Difficult) {
            this.Difficult = Difficult;
        }

        private String Difficult;
        private QuestionDifficulty(String Difficult){
            this.Difficult = Difficult;
        }
}
