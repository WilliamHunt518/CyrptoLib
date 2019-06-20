# CyrptoLib

## Outline

1. Cyrptors

Every cipher has a Cryptor object that has the following useful commands:

* setParams() - Argument varies depending on cipher
* encryptCall(String plainText) - Returns encrypted text
* decryptCall(String cipherText) - Returns decrypted text

2. Test Framework

The MasterTest object is a large test suite (Junit5) that tests a few known examples. It serves also as an ordered usage guide for the particular cipher. There may also be a dedicated doc in a later version.

3. (coming soon) Crackers

There will be a set of associated code breakers for the various ciphers.