package com.homebudget.rest.controller

import groovyx.net.http.RESTClient
import spock.lang.Specification


class UserRestControllerSpec extends Specification {
    def restClient = new RESTClient("http://localhost:8080")

//    def 'should return #result code (created) when trying to save record with all required fields'() {
//        when: 'try to save record with all required fields'
//        restClient.handler.failure=restClient.handler.success
//        def response = restClient.post(
//                path: '/users',
//                body: [firstName: firstNameVal,
//                       lastName : lastNameVal,
//                       password : passwordVal,
//                       email    : emailVal],
//                requestContentType: 'application/json')
//        then: 'server returns 200 code (created)'
//        response.status == result
//        where:
//        firstNameVal | lastNameVal | passwordVal                                                    | emailVal                ||      result
//        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com'       ||      200
//        'temp4'      | 'temp4'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp4@gmail.com'       ||      200
//    }

//    def 'should return 409 code when trying to save record with required fields'() {
//        given:
//        when: 'try to save record with all required fields'
//        restClient.handler.failure=restClient.handler.success
//        def response = restClient.post(
//                path: '/users',
//                body: [firstName: firstNameVal,
//                       lastName : lastNameVal,
//                       password : passwordVal,
//                       email    : emailVal],
//                requestContentType: 'application/json')
//        then: 'server returns 409 code'
//        response.status == result
//        where:
//        firstNameVal | lastNameVal | passwordVal                                                    | emailVal          || result
//        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 409
//    }
//
    def "should return suitable code when trying to save record"() {
        given:
        when: 'try to save record'
        restClient.handler.failure=restClient.handler.success
        def response = restClient.post(
                path: '/users',
                body: [firstName: firstNameVal,
                       lastName : lastNameVal,
                       password : passwordVal,
                       email    : emailVal],
                requestContentType: 'application/json')
        then: 'server returns suitable code'
        response.status == result
        where:
        firstNameVal | lastNameVal | passwordVal                                                    | emailVal          || result
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 200
        'temp4'      | 'temp4'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp4@gmail.com' || 200
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 409
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | ''                || 400
        ''           | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 400
        'temp3'      | ''          | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 400
        ''           | ''          | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3@gmail.com' || 400
        ''           | ''          | ''                                                             | 'temp3@gmail.com' || 400
        ''           | ''          | ''                                                             | ''                || 400
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3gmail.com'  || 400
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3.gmail.com' || 400
        'temp3'      | 'temp3'     | '$2a$10$kzpD8VZM1cn5Dlmsy1Uw..eOa34VghnWkiwcjrSXlfoVxAQezGjYu' | 'temp3'           || 400
    }
}
