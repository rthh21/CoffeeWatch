import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService, CartItem } from '../../services/cart';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.html'
})
export class HeaderComponent implements OnInit {
  @Input() currentPage: string = 'home';
  @Output() navigate = new EventEmitter<any>();

  cartItems: CartItem[] = [];
  isCartOpen = false;

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.cartService.cartItems$.subscribe(items => {
      this.cartItems = items;
    });
  }

  toggleCart() {
    this.isCartOpen = !this.isCartOpen;
  }

  get cartCount() {
    return this.cartItems.reduce((acc, item) => acc + item.quantity, 0);
  }

  removeFromCart(item: CartItem) {
    this.cartService.removeFromCart(item.watch.id, item.quantity);
    item.watch.stoc += item.quantity;
  }
}
