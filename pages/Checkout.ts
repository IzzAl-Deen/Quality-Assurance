import { Page } from '@playwright/test';

export class CheckoutPage {

    constructor(private page: Page) {}

    async fillCheckoutInfo(firstName: string, lastName: string, postalCode: string) {
        await this.page.fill('#first-name', firstName);
        await this.page.fill('#last-name', lastName);
        await this.page.fill('#postal-code', postalCode);
    }

    async continueCheckout() {
        await this.page.click('#continue');
    }

    async finishCheckout() {
        await this.page.click('#finish');
    }

    async getErrorMessage() {
        return await this.page
            .locator('[data-test="error"]')
            .textContent();
    }

    async getSuccessMessage() {
        return await this.page
            .locator('.complete-header')
            .textContent();
    }
}