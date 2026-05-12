import { Page, expect } from '@playwright/test';

export class InventoryPage {

    constructor(private page: Page) {}

    async addBackpackToCart() {
        await this.page.click('#add-to-cart-sauce-labs-backpack');
    }

    async addBikeLightToCart() {
        await this.page.click('#add-to-cart-sauce-labs-bike-light');
    }

    async openCart() {
        await this.page.click('.shopping_cart_link');
    }

    async getCartBadgeCount() {
        return await this.page.locator('.shopping_cart_badge').textContent();
    }

    async sortAZ() {
        await this.page.selectOption(
            '.product_sort_container',
            'az'
        );
    }

    async sortHighLow() {
        await this.page.selectOption(
            '.product_sort_container',
            'hilo'
        );
    }

    async getFirstItemName() {
        return await this.page
            .locator('.inventory_item_name')
            .first()
            .textContent();
    }

    async getFirstItemPrice() {
        return await this.page
            .locator('.inventory_item_price')
            .first()
            .textContent();
    }
}