import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-highlights',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './highlights.html'
})
export class HighlightsComponent {
  @Input() popularWatches: any[] = [];
  @Output() navigate = new EventEmitter<any>();
}
