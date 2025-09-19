import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { RouterModule } from "@angular/router";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "products",
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
  {
    path:"contact", loadComponent: () => import('./customers/contact/contact.component').then(m=> m.ContactComponent)
  }
];
