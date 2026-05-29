import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalog.html'
})
export class CatalogComponent {
  @Input() searchQuery: string = '';
  @Input() selectedBrand: string = '';
  @Input() maxPrice: number = 0;
  @Input() availableBrands: string[] = [];
  @Input() filteredWatches: any[] = [];

  @Output() updateSearch = new EventEmitter<any>();
  @Output() updateBrand = new EventEmitter<any>();
  @Output() updatePrice = new EventEmitter<any>();
  @Output() resetFilters = new EventEmitter<void>();
  @Output() addReview = new EventEmitter<any>();
  @Output() deleteWatch = new EventEmitter<any>();
}
