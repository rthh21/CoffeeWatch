import { Routes } from '@angular/router';
import { WatchListComponent } from './components/watch-list/watch-list';

export const routes: Routes = [
  { path: '', component: WatchListComponent },
  { path: 'watches', component: WatchListComponent }
];
