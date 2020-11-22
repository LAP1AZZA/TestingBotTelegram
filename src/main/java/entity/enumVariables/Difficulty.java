package entity.enumVariables;

import constants.QuestionConstants;

public enum Difficulty {Simple(QuestionConstants.QUESTION_DIFFICULTY_SIMPLE), Hard(QuestionConstants.QUESTION_DIFFICULTY_HARD);

        public String getDifficult() {
            return Difficult;
        }

        public void setTip(String Difficult) {
            this.Difficult = Difficult;
        }

        private String Difficult;
        private Difficulty(String Difficult){
            this.Difficult = Difficult;
        }
}
