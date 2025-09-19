import { Injectable, signal } from '@angular/core';
import { Product } from 'app/products/data-access/product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  public readonly cart = signal <Product []> ([]);

  public addToCart(product: Product){
  const currentCart = [...this.cart()];
  const existing = currentCart.find(p => p.id === product.id);
  if (existing) {
    existing.quantity +=1;
  }else 
  {
    currentCart.push({...product, quantity: 1});
  }
  this.cart.set(currentCart);
}
//remove from cart 

public removeFromCart(product: Product){
  this.cart.set(this.cart().filter(p => p.id !== product.id));
}

// clear cart

clear(){
  this.cart.set([]);
}
//Count of items
get count(): number {
  return this.cart().length;
}

  constructor() { }
}
