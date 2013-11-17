#encoding: utf-8
#language: en

Feature: Enter the access key over the commandline
  The user can enter his amazon access key over the commandline.

  Scenario Outline: Enter an accesskey
    Given an running test application
    When the user enter "accesskey=<key>"
    Then the user should get the message "<message>"

    Examples: Too short
      | key     | message                      |
      |         | The access key is empty!     |
      | A1B2C3  | The access key is to short!  |

    Examples: Letters and Numbers
      | key                   | message                                              |
      | ABCDEFABCDEFABCDEFAB  | The access key should contains letters and numbers ! |
      | ABCDEF123456ABCDEF78  | The access key is stored successfully!               |
