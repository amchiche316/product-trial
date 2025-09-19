import {
  Component,
  inject, signal
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { CartService } from "./shared/services/cart.service";
import {BadgeModule} from 'primeng/badge';
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { CommonModule } from "@angular/common";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, BadgeModule, ButtonModule, DialogModule, CommonModule],
})
export class AppComponent {
  title = "ALTEN SHOP";

  public readonly cartService = inject(CartService);
  public isCartVisible = signal(false);
  // expose cart count
  get cartCount(){
    return this.cartService.count;
  }

  toggleCart(){
    this.isCartVisible.set(!this.isCartVisible());
  }
}
