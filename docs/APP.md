# App Frontend


## Login Screen


<div style="display: flex; justify-content: center; align-items: center;">

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen.png" width="60%">
        <figcaption>Screenshot of the main support view</figcaption>
    </figure>

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen-wrong-credentials.png" width="60%">
        <figcaption>Screenshot of the main support view</figcaption>
    </figure>

</div>

The **Login Screen** in the **AurumBanking** app provides users with a simple interface to enter their email and password for authentication.

### Screen Elements

- **Penguin Mascot**: A friendly penguin mascot is displayed prominently at the top of the screen, providing a welcoming visual.
- **Email Field**: A text input field where the user can enter their email address.
- **Password Field**: A password input field where the user can enter their account password. The password is masked for security.
- **Login Button**: A button labeled "Login" that submits the email and password for authentication.


### Behavior

- **Successful Login**: If the user enters the correct email and password, they are authenticated and redirected to the app's main screen.

- **Failed Login**: If the user enters incorrect login credentials, a small error message is displayed at the bottom of the screen, informing the user that their credentials are incorrect. The message helps guide the user to try again with the correct information.

### Error Handling

- **Incorrect Credentials**: When the login attempt fails due to incorrect email or password, a small, clear error message is shown to the user below the login fields, helping them understand the issue and try again.


## Customer-Information Screen

## Depot-Service Screen

## Transaction-Service Screen

## Support Service Screen
### Support Option View


<div style="text-align: center;">

<figure>
    <img src="images/app/support-screen/support_main_view.png" width="40%">
    <figcaption>Screenshot of the main support view</figcaption>
</figure>

</div>

### Support Option View

The **Support Option View** in the AurumBanking app is designed to provide users with various options to resolve any issues or answer questions quickly and efficiently.

#### Options:

1. **Support Inquiry**:  
   Tap the mail icon to send a direct support request.

2. **Phone Support**:  
   Users can choose to contact customer support by phone. Tapping opens the integrated telephone app on the used mobile device. The Number ist automatically inserted.

3. **FAQ Section**:  
   The information icon leads users to the frequently asked questions (FAQ) section.


### Support Request View
<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view.png" width="100%">
        <figcaption>Screenshot of the support request screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-success.png" width="100%">
        <figcaption>Screenshot of the error message</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/suppot-request-view-fail.png" width="100%">
        <figcaption>Screenshot of the success message</figcaption>
    </figure>

</div>

#### Support Request Screen

This screen allows users to submit a support request by filling out the necessary information. The user can enter their legal name, the type of inquiry, their email address, and a message detailing their issue.

- **Legal Name**: The user's legal name, which is provided from the session manager.
- **Type of Inquiry**: Users can choose from a dropdown-menu (e.g., Überweisung, Konto, Benutzerdaten).
- **Email Address**: The user's contact email address, which also is provided.
- **Message**: A text field where the user can enter their specific issue or question.

At the bottom, there is a **Send** button that the user can tap to submit the support request. When the button is clicked, the user inputs get checked. For example the message field must be filled and the type must be selected.
At the top is a small menu bar with a check icon with the same functionality as the "senden" button

#### Support Request Submitted (Success) Screen

Once the user successfully submits their support request, a confirmation popup appears to inform them that their request has been received. This dialog reassures the user that their inquiry is being processed.

- **Confirmation Message**: "Anfrage gesendet" — informs the user that the support request has been sent.
- **Details**: The message also states that the request will be processed as quickly as possible.

The user can tap **OK** to dismiss the popup and return to the app.

#### Support Request Error (Failure) Screen

If there are any issues with the support request submission (e.g., missing required fields), the user is shown an error popup. This message prompts the user to complete the missing information.

- **Error Message**: "Fehler" — indicates that an error occurred during the submission process.
- **Details**: The message encourages the user to "Bitte füllen Sie alle Felder korrekt aus." (Please fill out all fields correctly).
- **Sending Error**: If there is an error from sending there is a different error message to inform the user.

The user can tap **OK** to dismiss the error and return to the form to correct the mistakes.

### Support Sub Screens

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/FAQ-Screen.png" width="100%">
        <figcaption>Screenshot of the FAQ screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen.png" width="100%">
        <figcaption>Screenshot of the telephone support</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-app-screen.png" width="100%">
        <figcaption>Screenshot of telephone app screen</figcaption>
    </figure>

</div>

#### FAQ Screen


This screen provides users with a Frequently Asked Questions (FAQ) section, helping them resolve common issues and inquiries. Users can expand the questions to read answers about setting up their account, the security of the app, and how to transfer money to another account.

**Key Elements:**

- **FAQ Cards:** The screen displays collapsible cards for each FAQ topic.
- **Questions Addressed:**
    - Setting up a new account in the app.
    - Security measures to protect the account.
    - Transferring money to another account.

---

#### Telephone Support Screen


This screen allows users to access AurumBanking's telephone support. The app provides the contact information, including the phone number and business hours (Monday to Friday, 8:00 - 20:00), enabling users to get direct support from customer service.

**Key Elements:**

- **Phone Number Display:** The contact number for customer service.
- **Support Hours:** Operating hours are clearly displayed for user reference.

---

#### Telephone App Screen


This screen shows the user's telephone dialer with the AurumBanking support number pre-filled. From this screen, the user can directly call the support line, create a new contact, add the number to an existing contact, or send an SMS.

**Key Elements:**

- **Dial Pad:** The dialer pad with the pre-filled AurumBanking support number.
- **Options Menu:** Additional options to create a new contact, add to an existing contact, or send an SMS.

### Screens in White Mode
<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

     <figure style="margin: 10px;">
        <img src="images/app/support-screen/support_main_view-whitemode.png" width="100%">
        <figcaption>Screenshot of the error message</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-whitemode.png" width="100%">
        <figcaption>Screenshot of the support request screen</figcaption>
    </figure>   

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen-whitemode.png" width="100%">
        <figcaption>Screenshot of the success message</figcaption>
    </figure>

</div>




## Settings Screen

### Settings Option Screen in AurumBanking App

<div style="display: flex; justify-content: center;">

<figure style="margin-right: 20px;">
    <img src="images/app/settings-screen/settings-option-screen.png" width="60%">
    <figcaption>Screenshot of the settings option screen</figcaption>
</figure>

<figure>
    <img src="images/app/settings-screen/settings-option-screen-whitemode.png" width="60%">
    <figcaption>Screenshot of the settings option screen in white mode</figcaption>
</figure>

</div>


The **Settings Option Screen** in the **AurumBanking** app serves as the main hub for users to manage their personal settings. From this screen, the user can access various important functions related to their account and the app's appearance.

#### Overview of the Settings Option Screen

Upon accessing the settings screen, the user is greeted by the app's mascot and a friendly prompt: **"Hier können Sie Einstellungen vornehmen!"**, which translates to "Here you can make settings!" in English. Below this prompt, the user is presented with three main options:

- **Personal Data (Persönliche Daten)**: Allows the user to view and edit their personal information, such as their name, address, and contact details.
- **Change Password (Passwort ändern)**: Provides a secure method for the user to change their account password.
- **Change Design (Design ändern)**: Enables the user to switch between different design modes (e.g., light and dark mode) for the app's appearance.

#### Additional Notes

- This screen acts as a central location for managing account settings and appearance options within the app.
- The user can quickly access this screen by tapping the gear icon located at the top right corner of the main screen.


### Change To Whitemode Screen

<div style="text-align: center;">

<figure>
    <img src="images/app/settings-screen/settings-whitemode-screen-record.gif" width="40%">
    <figcaption>Screenshot of the main support view</figcaption>
</figure>

</div>



The **AurumBanking** app provides a setting option to change the visual design of the app. This allows the user to switch between different themes, such as a light or dark mode, depending on their preference.

#### Overview of the White Mode Setting

The white mode (or light mode) setting is accessible from the **Settings** screen within the AurumBanking app. Upon selecting this option, the user interface of the app transitions to a lighter color palette, making it easier to view in well-lit environments.

By selecting the **Möchten Sie auf White-Mode/Dark-Mode wechseln** option, the user can alter the appearance of the app. The app will aks for confirmation and after clicking "verstanden", the app will switch to the wanted mode.

### Change Password Screen in AurumBanking App

    <div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-new-password-screen.png" width="60%">
        <figcaption>Screenshot of the new password screen</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-new-password-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the new password screen in white mode</figcaption>
    </figure>
    
    </div>

#### Change Password Screen in AurumBanking App

The **Change Password Screen** in the **AurumBanking** app allows users to securely update their account password. This screen provides an intuitive and user-friendly interface for changing the password in three simple steps.

#### Overview of the Change Password Screen

Upon accessing this screen, the user is prompted with the message **"Hier können Sie ihr Passwort ändern!"**, which translates to "Here you can change your password!" in English.

The user is presented with three fields:
- **Old Password (Altes Passwort)**: The user enters their current password.
- **New Password (Neues Passwort)**: The user enters a new password they would like to set.
- **Repeat New Password (Neues Passwort wiederholen)**: The user repeats the new password for confirmation.

At the bottom, the user can tap the **"Bestätigen"** (Confirm) button to complete the password change process.


#### Additional Notes

- The change password process is designed to ensure security, requiring the user to input their old password before confirming the new one.
- This screen can be accessed from the **Settings** menu by selecting the **Change Password** option.



### Personal Data Screen in AurumBanking App

    <div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-personal-data-screen.png" width="60%">
        <figcaption>Screenshot of the main support view</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-personal-data-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the main support view</figcaption>
    </figure>
    
    </div>



The **Personal Data Screen** in the **AurumBanking** app allows the user to view their account information in a detailed and structured format. This screen provides essential details about the user's account, such as their legal name, address, contact information, and account number.

#### Overview of the Personal Data Screen

On this screen, the user can see their information displayed clearly under the following categories:
- **Profile Picture**: Profile Picture of the user.
- **Legal Name**: The full legal name of the account holder.
- **Address**: The account holder's address, including street, postal code, city, and country.
- **Phone Number**: The user's contact phone number.
- **Email Address**: The registered email associated with the user's account.
- **Account Number**: The unique account number assigned to the user.

#### Additional Notes

- The user can access this screen by navigating through the **Settings** menu and selecting the **Personal Data** option.
- All the data shown here is private and secure, ensuring that only the account holder has access to their personal details.


