package entity.enumVariables;

import constants.QuestionConstants;

public enum Type
    {Single(QuestionConstants.QUESTION_SINGLE_TYPE), Multiple(QuestionConstants.QUESTION_MULTIPLE_TYPE), Open(QuestionConstants.QUESTION_OPEN_TYPE);

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        private String tip;
        private Type(String tip){
            this.tip = tip;
        }
    }

