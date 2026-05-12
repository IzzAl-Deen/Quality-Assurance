import { test, expect } from '@playwright/test';
import { InventoryPage } from '../pages/inventory';

test.describe('Sort Feature', () => {

    test.beforeEach(async ({ page }) => {
        await page.goto('https://www.saucedemo.com/inventory.html');
    });

    test('Sort products A-Z', async ({ page }) => {

        const inventory = new InventoryPage(page);

        await inventory.sortAZ();

        expect(await inventory.getFirstItemName())
            .toContain('Backpack');
    });

    test('Sort products by price high-low', async ({ page }) => {

        const inventory = new InventoryPage(page);

        await inventory.sortHighLow();

        expect(await inventory.getFirstItemPrice())
            .toContain('49.99');
    });
});