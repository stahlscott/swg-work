/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.jspsitelab;

/**
 *
 * @author Scott Stahl <stahl.scott@gmail.com>
 */
public class Interest {
    private int year;
        private String beginningPrincipal;
        private String endingPrincipal;
        private String interestEarned;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getBeginningPrincipal() {
            return beginningPrincipal;
        }

        public void setBeginningPrincipal(String beginningPrincipal) {
            this.beginningPrincipal = beginningPrincipal;
        }

        public String getEndingPrincipal() {
            return endingPrincipal;
        }

        public void setEndingPrincipal(String endingPrincipal) {
            this.endingPrincipal = endingPrincipal;
        }

        public String getInterestEarned() {
            return interestEarned;
        }

        public void setInterestEarned(String interestEarned) {
            this.interestEarned = interestEarned;
        }

}
