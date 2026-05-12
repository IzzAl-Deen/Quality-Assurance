import { test, expect } from '@playwright/test';
import { InventoryPage } from '../pages/inventory';
import { CartPage } from '../pages/Cart';

test.describe('Cart Feature', () => {

    test.beforeEach(async ({ page }) => {
        await page.goto('https://www.saucedemo.com/inventory.html');
    });

    test('Add single item to cart', async ({ page }) => {

        const inventory = new InventoryPage(page);

        await inventory.addBackpackToCart();

        expect(await inventory.getCartBadgeCount())
            .toBe('1');
    });

    test('Add multiple items to cart', async ({ page }) => {

        const inventory = new InventoryPage(page);

        await inventory.addBackpackToCart();
        await inventory.addBikeLightToCart();

        expect(await inventory.getCartBadgeCount())
            .toBe('2');
    });

    test('Verify item appears inside cart', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);

        await inventory.addBackpackToCart();

        await inventory.openCart();

        expect(await cart.getCartItemName())
            .toContain('Sauce Labs Backpack');
    });
});