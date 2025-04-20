package com.insider.journey.enums;

public class QAFiltersPage {
    public static final String SEE_ALL_QA_JOBS_BUTTON_X_PATH = "//a[normalize-space()='See all QA jobs']";
    public static final String FILTER_BY_LOCATION_CSS = "select[name='filter-by-location']";
    public static final String LOCATION_DROP_DOWN_X_PATH = "//span[@aria-labelledby='select2-filter-by-location-container']";
    public static final String ISTANBUL_OPTION_X_PATH = "//li[contains(@class,'select2-results__option') and text()='Istanbul, Turkiye']";
    public static final String DEPARTMENT_DROP_DOWN_X_PATH = "//select[@name='filter-by-department']";
    public static final String POSITION_LIST_X_PATH = "//div[contains(@class,'position-list')]";
    public static final String JOB_CARDS_X_PATH = "//div[contains(@class,'position-list')]//div[contains(@class,'position')]";
    public static final String VIEW_ROLES_X_PATH = "//*[@id='jobs-list']/div[1]/div/a";
    public static final String FIRST_JOB_CARD_X_PATH = "//*[@id='jobs-list']/div[1]";
    public static final String JOB_CARD_X_PATH = "//div[contains(@class,'position-list-item-wrapper')]";
}

