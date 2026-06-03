import { Component, Input, Output, EventEmitter, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CartService } from '../../services/cart';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './catalog.html'
})
export class CatalogComponent {
  private cartService = inject(CartService);

  @Input() searchQuery: string = '';
  @Input() selectedBrand: string = '';
  @Input() maxPrice: number = 0;
  @Input() availableBrands: string[] = [];
  @Input() filteredWatches: any[] = [];

  @Output() updateSearch = new EventEmitter<any>();
  @Output() updateBrand = new EventEmitter<any>();
  @Output() updatePrice = new EventEmitter<any>();
  @Output() resetFilters = new EventEmitter<void>();
  @Output() deleteWatch = new EventEmitter<any>();

  quantities: {[id: string]: number} = {};

  getQty(id: string): number {
    return this.quantities[id] || 1;
  }

  setQty(id: string, event: Event): void {
    const val = parseInt((event.target as HTMLInputElement).value, 10);
    this.quantities[id] = val > 0 ? val : 1;
  }

  incrementQty(watch: any): void {
    const current = this.getQty(watch.id);
    if (current < watch.stock) {
      this.quantities[watch.id] = current + 1;
    }
  }

  decrementQty(watch: any): void {
    const current = this.getQty(watch.id);
    if (current > 1) {
      this.quantities[watch.id] = current - 1;
    }
  }

  buyWatch(watch: any): void {
    const qty = this.getQty(watch.id);
    if (watch.stock > 0 && qty <= watch.stock) {
      this.cartService.addToCart(watch, qty);
      this.quantities[watch.id] = 1;
    }
  }
}
