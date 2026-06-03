import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface CartItem {
  watch: any;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = new BehaviorSubject<CartItem[]>([]);
  cartItems$ = this.cartItems.asObservable();

  addToCart(watch: any, quantity: number) {
    const currentItems = this.cartItems.value;
    const existingItemIndex = currentItems.findIndex(item => item.watch.id === watch.id);

    if (existingItemIndex > -1) {
      currentItems[existingItemIndex].quantity += quantity;
    } else {
      currentItems.push({ watch, quantity });
    }
    this.cartItems.next([...currentItems]);
  }

  removeFromCart(watchId: number, quantity: number) {
    let currentItems = this.cartItems.value;
    const existingItemIndex = currentItems.findIndex(item => item.watch.id === watchId);

    if (existingItemIndex > -1) {
      currentItems[existingItemIndex].quantity -= quantity;
      if (currentItems[existingItemIndex].quantity <= 0) {
        currentItems.splice(existingItemIndex, 1);
      }
    }
    this.cartItems.next([...currentItems]);
  }

  getCartItems(): CartItem[] {
    return this.cartItems.value;
  }
}
