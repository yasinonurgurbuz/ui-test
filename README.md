# Insider QA Test Automation Project

This project contains automated tests for the Insider website, specifically focusing on the Careers section and QA job listings.

## Project Overview

This test automation project is designed to verify the functionality of the Insider website's Careers section, with a specific focus on Quality Assurance job listings in Istanbul, Turkey.

## Test Scenarios

1. Verify Insider home page accessibility
2. Validate Careers page navigation and content blocks
3. Check QA job listings in Istanbul, Turkey
4. Verify job listing details and filters
5. Validate job application form redirection

## Technical Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- ChromeDriver
- GeckoDriver (Firefox)

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome and Firefox browsers installed
- Git

## Setup Instructions

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd ui-test
```

3. Install dependencies:
```bash
mvn clean install
```

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test:
```bash
mvn test -Dtest=QACareersTest
```

### Run with specific browser:
```bash
mvn test -Dbrowser=chrome
# or
mvn test -Dbrowser=firefox
```

## Test Configuration

- Browser selection is configurable through the `browser` parameter
- Screenshots are automatically captured on test failures
- Test reports are generated in the `target` directory

## Page Object Model (POM)

The project follows the Page Object Model design pattern:
- Each page has its own class
- Page elements are defined using WebDriver locators
- Page actions are encapsulated in methods
- Common utilities are shared across pages

## Test Cases

1. **Home Page Verification**
   - Navigate to https://useinsider.com/
   - Verify home page is loaded successfully

2. **Careers Page Navigation**
   - Click on "Company" menu
   - Select "Careers"
   - Verify Careers page sections:
     - Locations
     - Teams
     - Life at Insider

3. **QA Jobs Filtering**
   - Navigate to QA careers page
   - Apply filters:
     - Location: Istanbul, Turkey
     - Department: Quality Assurance
   - Verify job listings

4. **Job Details Verification**
   - Verify all listed jobs contain:
     - Position: Quality Assurance
     - Department: Quality Assurance
     - Location: Istanbul, Turkey

5. **Application Form Redirection**
   - Click "View Role" button
   - Verify redirection to Lever Application form

## Error Handling

- Screenshots are automatically captured on test failures
- Detailed error messages are logged
- Test execution continues on non-critical failures

## Reporting

- TestNG reports are generated in the `target` directory
- Screenshots of failed tests are saved in the `screenshots` directory

## Contributing

1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License

This project is licensed under the MIT License.