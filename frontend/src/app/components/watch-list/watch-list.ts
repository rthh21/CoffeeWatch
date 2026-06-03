import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WatchService } from '../../services/watch';
import { CartService } from '../../services/cart';

@Component({
  selector: 'app-watch-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './watch-list.html',
  styleUrl: './watch-list.scss'
})
export class WatchListComponent implements OnInit {
  watches: any[] = [];
  quantities: { [watchId: number]: number } = {};

  constructor(private watchService: WatchService, private cartService: CartService) {}

  ngOnInit(): void {
    this.watchService.getAllWatches().subscribe({
      next: (data) => {
        this.watches = data;
        this.watches.forEach(w => this.quantities[w.id] = 1);
      },
      error: (err) => {
        console.error('Error fetching watches:', err);
      }
    });
  }

  decreaseQuantity(watchId: number) {
    if (this.quantities[watchId] > 1) {
      this.quantities[watchId]--;
    }
  }

  increaseQuantity(watch: any) {
    if (this.quantities[watch.id] < watch.stoc) {
      this.quantities[watch.id]++;
    }
  }

  orderWatch(watch: any) {
    const qty = this.quantities[watch.id];
    if (qty > 0 && watch.stoc >= qty) {
      this.cartService.addToCart(watch, qty);
      watch.stoc -= qty;
      this.quantities[watch.id] = 1;
    }
  }
}
