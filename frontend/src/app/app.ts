import { Component, signal, computed, ChangeDetectionStrategy, HostListener, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

import { HeaderComponent } from './components/header/header';
import { HeroComponent } from './components/hero/hero';
import { HighlightsComponent } from './components/highlights/highlights';
import { FeaturesComponent } from './components/features/features';
import { CatalogComponent } from './components/catalog/catalog';
import { FooterComponent } from './components/footer/footer';
import { ToastComponent } from './components/toast/toast';
import { GoToTopComponent } from './components/go-to-top/go-to-top';
import { AdminComponent } from './components/admin/admin';
import { OrdersComponent } from './components/orders/orders';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule, 
    HeaderComponent, 
    HeroComponent, 
    HighlightsComponent, 
    FeaturesComponent, 
    CatalogComponent, 
    FooterComponent, 
    ToastComponent, 
    GoToTopComponent,
    AdminComponent,
    OrdersComponent
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App implements OnInit {
  private http = inject(HttpClient);
  
  currentPage = signal<'home' | 'catalog' | 'admin' | 'comenzi'>('home');
  showGoToTop = signal(false);
  
  toastMessage = signal<string | null>(null);

  searchQuery = signal<string>('');
  selectedBrand = signal<string>('');
  maxPrice = signal<number>(300000);
  watchToDelete = signal<any | null>(null);

  // Signals for real data from the backend
  popularWatches = signal<any[]>([]);
  catalogWatches = signal<any[]>([]);

  ngOnInit() {
    this.loadWatches();
  }

  loadWatches() {
    console.log('Starting watch loading...');
    this.http.get<any[]>('/api/ceasuri').subscribe({
      next: (data) => {
        console.log('Data received from backend:', data.length, 'watches');
        if (!Array.isArray(data)) {
          console.error('Received data is not an array!');
          return;
        }

        const mapped = data.map(w => ({
          id: w.id || Math.random().toString(36),
          brand: w.brand?.nume || 'Unknown',
          name: w.numeModel || 'Unknown Model',
          price: w.pret || 0,
          type: w.rezervaPutereOre ? 'Mechanical' : (w.sistemOperare || w.capacitateBaterieMah ? 'Smartwatch' : 'Mechanical'),
          image: this.getWatchImage(w)
        }));
        
        this.catalogWatches.set(mapped);
        this.popularWatches.set(mapped.slice(0, 4));
        console.log('Mapping completed successfully.');
      },
      error: (err) => {
        this.showToast('Error loading data from backend.');
        console.error('HTTP Error:', err);
      }
    });
  }

  // Mapping for optimized local WebP images - real shop style
  getWatchImage(watch: any): string {
    const id = watch.id || '';
    const brandName = (watch.brand?.nume || watch.brand || '').toString().toLowerCase();
    const modelName = (watch.numeModel || watch.name || '').toString().toLowerCase();
    
    // 1. Mapping for specific IDs (Real Products)
    const idMap: Record<string, string> = {
      'C1': 'watches/r_dj.webp',     // Rolex Datejust
      'R2': 'watches/r_sub.webp',    // Rolex Submariner
      'R3': 'watches/r_sub.webp',    // Rolex Daytona (fallback to sub)
      'R4': 'watches/r_sub.webp',    // Rolex GMT
      'O2': 'watches/o_sm.webp',     // Omega Seamaster
      'C5': 'watches/o_sm.webp',     // Omega Speedmaster
      'S1': 'watches/s_div.webp',    // Seiko Turtle
      'S2': 'watches/s_div.webp',    // Seiko Presage
      'S3': 'watches/s_div.webp',    // Seiko 5
      'CA1': 'watches/ca_g.webp',    // Casio G-Shock
      'CA2': 'watches/ca_g.webp',    // Casio F-91W
      'T1': 'watches/t_prx.webp',    // Tissot PRX
      'C10': 'watches/p_naut.webp',  // Patek Nautilus
      'C3': 'watches/smart.webp',    // Apple Watch
      'C9': 'watches/smart.webp',    // Samsung Watch
      'C7': 'watches/smart.webp'     // Garmin
    };

    if (idMap[id]) return idMap[id];

    // 2. Fallback by categories (if ID is not mapped)
    if (brandName.includes('rolex')) return 'watches/rolex.webp';
    if (brandName.includes('omega')) return 'watches/omega.webp';
    if (brandName.includes('seiko')) return 'watches/seiko.webp';
    if (brandName.includes('casio')) return 'watches/casio.webp';
    
    if (watch.sistemOperare || watch.capacitateBaterieMah || modelName.includes('watch')) {
      return 'watches/smart.webp';
    }

    return 'watches/luxury.webp';
  }

  availableBrands = computed(() => {
    const brands = this.catalogWatches().map(w => w.brand);
    return [...new Set(brands)].sort();
  });

  filteredWatches = computed(() => {
    const search = this.searchQuery().toLowerCase();
    const brand = this.selectedBrand();
    const maxP = this.maxPrice();

    return this.catalogWatches().filter(watch => {
      const matchSearch = watch.name.toLowerCase().includes(search) || watch.brand.toLowerCase().includes(search);
      const matchBrand = brand === '' || watch.brand === brand;
      const matchPrice = watch.price <= maxP;
      
      return matchSearch && matchBrand && matchPrice;
    });
  });

  updateSearch(event: Event) {
    this.searchQuery.set((event.target as HTMLInputElement).value);
  }

  updateBrand(event: Event) {
    this.selectedBrand.set((event.target as HTMLSelectElement).value);
  }

  updatePrice(event: Event) {
    this.maxPrice.set(parseInt((event.target as HTMLInputElement).value, 10));
  }

  resetFilters() {
    this.searchQuery.set('');
    this.selectedBrand.set('');
    this.maxPrice.set(300000);
  }

  deleteWatch(watch: any) {
    this.watchToDelete.set(watch);
  }

  confirmDelete() {
    const watch = this.watchToDelete();
    if (!watch) return;

    this.http.delete(`/api/ceasuri/${watch.id}`).subscribe({
      next: () => {
        this.catalogWatches.update(watches => watches.filter(w => w.id !== watch.id));
        this.popularWatches.update(watches => watches.filter(w => w.id !== watch.id));
        this.showToast(`Product ${watch.brand} has been permanently deleted from the database.`);
        this.watchToDelete.set(null);
      },
      error: (err) => {
        this.showToast(`Error deleting product ${watch.brand}.`);
        console.error(err);
        this.watchToDelete.set(null);
      }
    });
  }

  cancelDelete() {
    this.watchToDelete.set(null);
  }

  addReview(watch: any) {
    this.showToast(`Opening review form for ${watch.name}...`);
  }

  showToast(message: string) {
    this.toastMessage.set(message);
    setTimeout(() => {
      this.toastMessage.set(null);
    }, 3500);
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.showGoToTop.set(window.scrollY > 400);
  }

  navigate(page: 'home' | 'catalog' | 'admin' | 'comenzi') {
    this.currentPage.set(page);
    this.scrollToTop();
  }

  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
