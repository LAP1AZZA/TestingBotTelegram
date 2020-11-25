package entity.enumVariables;

import constants.QuestionConstants;

public enum QuestionType
    {SINGLE(QuestionConstants.QUESTION_SINGLE_TYPE), MULTIPLE(QuestionConstants.QUESTION_MULTIPLE_TYPE), OPEN(QuestionConstants.QUESTION_OPEN_TYPE);

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        private String tip;
        private QuestionType(String tip){
            this.tip = tip;
        }
    }

