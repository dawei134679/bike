package com.coolu.nokelock.bike.bean;

import java.util.ArrayList;

/**
 * @author Learning
 * @date 2019/3/13
 */
public class BalanceAddBean {
    private int errorCode;
    private String message;
    private ArrayList<CardInfo> result;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CardInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<CardInfo> result) {
        this.result = result;
    }

    public class CardInfo{
        private String cardName;
        private String cardContext;
        private int cardMoney;
        private int cardDate;

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardContext() {
            return cardContext;
        }

        public void setCardContext(String cardContext) {
            this.cardContext = cardContext;
        }

        public int getCardMoney() {
            return cardMoney;
        }

        public void setCardMoney(int cardMoney) {
            this.cardMoney = cardMoney;
        }

        public int getCardDate() {
            return cardDate;
        }

        public void setCardDate(int cardDate) {
            this.cardDate = cardDate;
        }
    }
}
