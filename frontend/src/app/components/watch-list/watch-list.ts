import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WatchService } from '../../services/watch';

@Component({
  selector: 'app-watch-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './watch-list.html',
  styleUrl: './watch-list.scss'
})
export class WatchListComponent implements OnInit {
  watches: any[] = [];

  constructor(private watchService: WatchService) {}

  ngOnInit(): void {
    this.watchService.getAllWatches().subscribe({
      next: (data) => {
        this.watches = data;
      },
      error: (err) => {
        console.error('Error fetching watches:', err);
      }
    });
  }
}
