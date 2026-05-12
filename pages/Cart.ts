import { Page } from '@playwright/test';

export class CartPage {

    constructor(private page: Page) {}

    async removeBackpack() {
        await this.page.click('#remove-sauce-labs-backpack');
    }

    async removeBikeLight() {
        await this.page.click('#remove-sauce-labs-bike-light');
    }

    async checkout() {
        await this.page.click('#checkout');
    }

    async getCartItemsCount() {
        return await this.page.locator('.cart_item').count();
    }

    async getCartItemName() {
        return await this.page
            .locator('.inventory_item_name')
            .first()
            .textContent();
    }
}