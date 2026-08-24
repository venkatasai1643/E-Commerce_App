import { Routes } from '@angular/router';
import { authenticationRoutes } from './features/authentication/authentication.routes';
import { CustomerDashboard } from './features/customer/customer-dashboard/customer-dashboard';
import { SellerDashboard } from './features/seller/seller-dashboard/seller-dashboard';
import { DeliveryDashboard } from './features/delivery/delivery-dashboard/delivery-dashboard';
import { AdminDashboard } from './features/admin/admin-dashboard/admin-dashboard';

export const routes: Routes = [
    {
        path: 'auth',
        children: authenticationRoutes
    },

    {
        path: 'customer',
        component : CustomerDashboard
    },

     {
        path: 'seller',
        component : SellerDashboard
    },

     {
        path: 'delivery',
        component : DeliveryDashboard
    },

     {
        path: 'admin',
        component : AdminDashboard
    }
];
