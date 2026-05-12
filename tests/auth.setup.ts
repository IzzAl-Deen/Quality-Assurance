import { test as setup, expect } from '@playwright/test';

setup('authenticate', async ({ page }) => {

    await page.goto('https://www.saucedemo.com/');

    await page.fill('#user-name', process.env.SAUCE_USERNAME!);

    await page.fill('#password', process.env.SAUCE_PASSWORD!);

    await page.click('#login-button');
    
    await page.waitForURL('**/inventory.html');

    await page.context().storageState({
        path: 'playwright/.auth/user.json'
    });
});