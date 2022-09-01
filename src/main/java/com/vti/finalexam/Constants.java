package com.vti.finalexam;

public class Constants {

    public interface AUTHENTICATION {
        String AUTHORIZATION_TOKEN = "Authorization";
    }

    public interface ROLE_TYPE {
        String ADMIN = "ADMIN";
        String EMPLOYEE = "EMPLOYEE";
        String MANAGER = "MANAGER";
    }

    public interface ACCOUNT {
        String ID = "id";
        String USERNAME = "username";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String ROLE = "role";
    }

    public interface DEPARTMENT {
        String ID = "id";
        String NAME = "name";
        String TOTAL_MEMBER = "totalMember";
        String TYPE = "type";
        String CREATED_DATE = "createdDate";
    }

    public interface OPERATOR {
        String EQUALS = "equals";
        String NOT_EQUALS = "notEquals";
        String CONTAINS = "contains";
        String NOT_CONTAINS = "notContains";
        String GREATER_THAN = "greaterThan";
        String LESS_THAN = "lessThan";
        String GREATER_THAN_OR_EQUALS = "greaterThanOrEquals";
        String LESS_THAN_OR_EQUALS = "lessThanOrEquals";
    }

    public interface ROLE {
        String ADMIN = "ADMIN";
        String EMPLOYEE = "EMPLOYEE";
        String MANAGER = "MANAGER";
    }

    public interface API {
        String BASE_API_V1 = "/api/v1";
        String ACCOUNTS = "/accounts";
        String DEPARTMENTS = "/departments";
    }
}
