import { test, expect } from '@playwright/test';
import { InventoryPage } from '../pages/inventory';
import { CartPage } from '../pages/Cart';

test.describe('Remove Feature', () => {

    test.beforeEach(async ({ page }) => {
        await page.goto('https://www.saucedemo.com/inventory.html');
    });

    test('Remove single item', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);

        await inventory.addBackpackToCart();

        await inventory.openCart();

        await cart.removeBackpack();

        expect(await cart.getCartItemsCount())
            .toBe(0);
    });

    test('Remove multiple items one by one', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);

        await inventory.addBackpackToCart();
        await inventory.addBikeLightToCart();

        await inventory.openCart();

        expect(await cart.getCartItemsCount())
            .toBe(2);

        await cart.removeBackpack();

        expect(await cart.getCartItemsCount())
            .toBe(1);

        await cart.removeBikeLight();

        expect(await cart.getCartItemsCount())
            .toBe(0);
    });
});