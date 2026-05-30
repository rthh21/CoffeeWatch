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
  changeDetection: ChangeDetectionStrategy.Default,
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App implements OnInit {
  private http = inject(HttpClient);
  
  currentPage = signal<'home' | 'catalog' | 'admin' | 'orders'>('home');
  showGoToTop = signal(false);
  
  toastMessage = signal<string | null>(null);

  searchQuery = signal<string>('');
  selectedBrand = signal<string>('');
  maxPrice = signal<number>(300000);
  watchToDelete = signal<any | null>(null);

  
  popularWatches = signal<any[]>([]);
  catalogWatches = signal<any[]>([]);

  ngOnInit() {
    this.loadWatches();
  }

  
  isLocalOrValidImage(url: string | undefined, id: string | undefined): boolean {
    if (!url) return false;
    if (url.startsWith('watches/')) return true;
    
    
    
    
    const seededIds = [
      'C1', 'C2', 'C3', 'C4', 'C5', 'C6', 'C7', 'C8', 'C9', 'C10',
      'R2', 'R3', 'R4', 'R5',
      'O2', 'O3', 'O4', 'O5',
      'L1', 'T1', 'S1', 'S2', 'S3', 'S4', 'S5',
      'CA1', 'CA2', 'CA3', 'CA4', 'CA5'
    ];
    if (id && seededIds.includes(id.toUpperCase())) {
      return false; 
    }
    
    return url.startsWith('http');
  }

  loadWatches() {
    console.log('Starting watch loading...');
    this.http.get<any[]>('/api/watches').subscribe({
      next: (data) => {
        console.log('Data received from backend:', data.length, 'watches');
        if (!Array.isArray(data)) {
          console.error('Received data is not an array!');
          return;
        }

        const mapped = data.map(w => ({
          id: w.id || Math.random().toString(36),
          
          brand: w.brand?.name || w.brand || 'Unknown',
          
          name: w.modelName || w.name || 'Unknown Model',
          
          price: w.price || 0,
          
          type: w.type || (w.powerReserve || w.mechanismType ? 'Mechanical' : (w.operatingSystem || w.batteryCapacity ? 'Smartwatch' : 'Mechanical')),
          
          image: this.isLocalOrValidImage(w.imageUrl, w.id) ? w.imageUrl : this.getWatchImage(w)
        }));
        
        this.catalogWatches.update(watches => [...watches, ...mapped]);
        this.popularWatches.update(watches => [...watches, ...mapped.slice(0, 4)]);
        console.log('Mapping completed successfully.');
      },
      error: (err) => {
        this.showToast('Error loading data from backend.');
        console.error('HTTP Error:', err);
      }
    });
  }

  
  getWatchImage(watch: any): string {
    const id = (watch.id || '').toString().toUpperCase();
    const brandName = (watch.brand?.name || watch.brand?.nume || watch.brand || '').toString().toLowerCase();
    const modelName = (watch.modelName || watch.numeModel || watch.name || '').toString().toLowerCase();
    
    
    if (id === 'C1') return 'watches/c1.webp';
    if (id === 'C2') return 'watches/c2.webp';
    if (id === 'C3') return 'watches/c3.webp';
    if (id === 'C4') return 'watches/luxury.webp'; 
    if (id === 'C5') return 'watches/c5.webp';
    if (id === 'C6') return 'watches/c6.webp';
    if (id === 'C7') return 'watches/c7.webp';
    if (id === 'C8') return 'watches/c8.webp';
    if (id === 'C9') return 'watches/c9.webp';
    if (id === 'C10') return 'watches/p_naut.webp';
    
    if (id === 'R2') return 'watches/r2.webp';
    if (id === 'R3') return 'watches/r3.webp';
    if (id === 'R4') return 'watches/r4.webp';
    if (id === 'R5') return 'watches/r5.webp';
    
    if (id === 'O2') return 'watches/o2.webp';
    if (id === 'O3') return 'watches/o3.webp';
    if (id === 'O4') return 'watches/o4.webp';
    if (id === 'O5') return 'watches/o5.webp';
    
    if (id === 'L1') return 'watches/l1.webp';
    if (id === 'T1') return 'watches/t_prx.webp';
    
    
    if (id === 'S1') return 'watches/s_div.webp'; 
    if (id === 'S2') return 'watches/s2.webp'; 
    if (id === 'S3') return 'watches/s3.webp'; 
    if (id === 'S4') return 'watches/s4.webp'; 
    if (id === 'S5') return 'watches/s5.webp'; 
    
    
    if (id === 'CA1') return 'watches/ca1.webp'; 
    if (id === 'CA2') return 'watches/ca2.webp'; 
    if (id === 'CA3') return 'watches/ca3.webp'; 
    if (id === 'CA4') return 'watches/ca4.webp'; 
    if (id === 'CA5') return 'watches/ca5.webp'; 
    
    
    if (brandName.includes('rolex') || modelName.includes('datejust') || modelName.includes('submariner')) return 'watches/r_sub.webp';
    if (brandName.includes('omega') || modelName.includes('speedmaster') || modelName.includes('seamaster')) return 'watches/o_sm.webp';
    if (brandName.includes('seiko') || modelName.includes('turtle')) return 'watches/s_div.webp';
    if (brandName.includes('patek') || modelName.includes('nautilus')) return 'watches/p_naut.webp';
    if (brandName.includes('casio') || brandName.includes('g-shock')) return 'watches/ca_g.webp';
    if (brandName.includes('tissot') || modelName.includes('prx')) return 'watches/t_prx.webp';
    
    if (watch.operatingSystem || watch.sistemOperare || modelName.includes('watch') || brandName.includes('apple') || brandName.includes('garmin')) {
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

  onAddWatch(watch: any) {
    this.http.post('/api/watches', watch).subscribe({
      next: () => {
        this.catalogWatches.update(watches => [...watches, {
          ...watch,
          image: this.isLocalOrValidImage(watch.imageUrl, watch.id) ? watch.imageUrl : this.getWatchImage(watch)
        }]);
        this.showToast(`Product ${watch.name} added successfully!`);
        this.navigate('catalog');
      },
      error: (err) => {
        this.showToast('Error adding product.');
        console.error(err);
        
        this.catalogWatches.update(watches => [...watches, {
          ...watch,
          image: this.isLocalOrValidImage(watch.imageUrl, watch.id) ? watch.imageUrl : this.getWatchImage(watch)
        }]);
        this.showToast(`Product ${watch.name} added (Local mode).`);
        this.navigate('catalog');
      }
    });
  }

  deleteWatch(watch: any) {
    this.watchToDelete.set(watch);
  }

  confirmDelete() {
    const watch = this.watchToDelete();
    if (!watch) return;

    this.http.delete(`/api/watches/${watch.id}`).subscribe({
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

  navigate(page: 'home' | 'catalog' | 'admin' | 'orders') {
    this.currentPage.set(page);
    this.scrollToTop();
  }

  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
