# EncryptionProgram
This is the encryption program that I wrote in 10th grade using Java. This repo included 4 runnable programs (main function):

Encryption_Advance.java -- The program that encrypts a message with user-provided passcode
encryption_small.java ---- The same encryption program but it fits on a smaller screen
Decryption.java ---------- The program that decrypts the encrypted message
decryption_small.java ---- the same decryption program but it fits on a smaller screen

3 Methods of encryption are provided:
Pro-Encryption:
	A one-way encryption method designed by Wyett which encrypts the message according to the passcode user provided, the level of security and required processing power increase as the number of characters in the passcode. This encryption method provides 2 level of securities, one may Brute-force the passcode for Low-level security if he has excellent understanding of the source code and access to a computer with extremely high processing power. The high-level encryption provides higher level of security; however, normal users usually do not have the process power to decrypt the message once the passcode is more than 11 characters long.


Substitution:
	First adapted by Julius Caesar to encrypt message. Provides low level of security that would suffice most of the users. It is true that one may brute-force the passcode if he has excellent understanding of this program's source code and advance programing skills.


RSA (Rivest�Shamir�Adleman):
A public-key cryptosystems designed by Ron Rivest, Adi Shamir, and Leonard Adleman that is widely used. Provide high level of security. However, it challenges the computer�s processing power especially the High-level encryption. Recommended for important short messages.
