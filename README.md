# MercariUiTest

MercariUiTest is an automated UI testing project built using Java and Maven. It leverages Selenium for browser automation, TestNG for testing framework management, and ExtentReports for generating interactive test reports.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Dependencies](#dependencies)
- [Build Configuration](#build-configuration)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Installation
To set up the project, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/kamleshbkmuthu/MercariTest.git
   cd MercariUiTest

2. Install Maven: 
   Ensure you have Maven installed. If not, you can download it from Maven's official website.
3. Install Dependencies:
   Use Maven to download and install the required dependencies:
   ```bash
   mvn install

## Usage
To run the automated tests, execute the following command:
   ```bash
   mvn clean test
   ```
## Features:
   - Automated UI tests using Selenium WebDriver.
   - Detailed test reporting with ExtentReports.
   - Automatic browser driver management with WebDriverManager.
   - Support for TestNG test suite configuration.
## Dependencies:
The project relies on several key libraries, as defined in the pom.xml:

   - TestNG: A testing framework for managing and executing tests.
   - Selenium Java: A powerful suite for browser automation.
   - ExtentReports: A reporting library for creating interactive test reports.
   - WebDriverManager: A library that manages browser drivers automatically.
## Build Configuration:
The project is configured to use the Maven Surefire Plugin for executing tests defined in the testng.xml file:
## Contributing
Contributions are welcome! If you have suggestions or improvements, please create an issue or submit a pull request.
## License
This project is licensed under the MIT License. See the LICENSE file for details.
## Contact
For inquiries, feel free to reach out at kamleshmuthu5@gmail.com.