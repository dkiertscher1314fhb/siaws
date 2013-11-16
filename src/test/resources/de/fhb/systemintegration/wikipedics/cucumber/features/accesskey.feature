#encoding: utf-8
#language: en

Feature: Enter the accesskey over the commandline
  The user can enter his amazon accesskey over the commandline.

  Scenario: Enter an empty accesskey
    Given an running test application
    When the user enter an empty accesskey
    Then the user should get a message that the acceskey is to short