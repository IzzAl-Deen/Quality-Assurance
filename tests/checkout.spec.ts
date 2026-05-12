import { test, expect } from '@playwright/test';
import { InventoryPage } from '../pages/inventory';
import { CartPage } from '../pages/Cart';
import { CheckoutPage } from '../pages/Checkout';

test.describe('Checkout Feature', () => {

    test.beforeEach(async ({ page }) => {
        await page.goto('https://www.saucedemo.com/inventory.html');
    });

    test('Checkout single item successfully', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);
        const checkout = new CheckoutPage(page);

        await inventory.addBackpackToCart();

        await inventory.openCart();

        await cart.checkout();

        await checkout.fillCheckoutInfo(
            'Choom',
            'QA',
            '123'
        );

        await checkout.continueCheckout();

        await expect(page).toHaveURL(/checkout-step-two/);
    });

    test('Checkout with multiple items', async ({ page }) => {
        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);
        const checkout = new CheckoutPage(page);

        await inventory.addBackpackToCart();
        await inventory.addBikeLightToCart();

        await inventory.openCart();
        await cart.checkout();

        await checkout.fillCheckoutInfo(
            'Choom',
            'QA',
            '123'
        );
        
        await checkout.continueCheckout();


        await expect(page).toHaveURL(/checkout-step-two/);
    });


    test('Checkout with empty fields', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);
        const checkout = new CheckoutPage(page);

        await inventory.addBackpackToCart();

        await inventory.openCart();

        await cart.checkout();

        await checkout.continueCheckout();

        expect(await checkout.getErrorMessage())
            .toContain('Error');
    });

    test('Finish checkout successfully', async ({ page }) => {

        const inventory = new InventoryPage(page);
        const cart = new CartPage(page);
        const checkout = new CheckoutPage(page);

        await inventory.addBackpackToCart();

        await inventory.openCart();

        await cart.checkout();

        await checkout.fillCheckoutInfo(
            'Choom',
            'QA',
            '123'
        );

        await checkout.continueCheckout();

        await checkout.finishCheckout();

        expect(await checkout.getSuccessMessage())
            .toContain('Thank you');
    });
});