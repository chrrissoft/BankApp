package org.kodigo.java10.bankapp;


interface DateResolver {
    Date resolve();
}

class DateResolverImpl implements DateResolver {
    @Override
    public Date resolve() {
        java.util.Date date = new java.util.Date();
        int day = date.getDay();
        int year = date.getYear();
        int month = date.getMonth();
        return new Date(day, month, year);
    }
}
