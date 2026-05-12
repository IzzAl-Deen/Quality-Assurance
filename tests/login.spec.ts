import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/loginPage';


test.describe('Login Feature', () => {

    test.beforeEach(async ({ page }) => {
        const loginPage = new LoginPage(page);
        await loginPage.goto();
    });

    test('Valid Login', async ({ page }) => {

        const loginPage = new LoginPage(page);

        await loginPage.login(
            process.env.SAUCE_USERNAME!,
            process.env.SAUCE_PASSWORD!
        );

        await expect(page).toHaveURL(/inventory/);
    });


    const invalidUsers = [
        ['wrong_user', 'secret_sauce'],
        ['some_non_existent_user', 'some_password'],
        ['', '']
    ];
    for (const [username, password] of invalidUsers) {
        test(`Invalid Login for ${username}`, async ({ page }) => {
            const loginPage = new LoginPage(page);

            await loginPage.login(
                username,
                password
            );

            await expect(page.locator('.error-message-container')).toBeVisible();

        });
    }
});