import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-features',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './features.html'
})
export class FeaturesComponent {
  @Output() navigate = new EventEmitter<any>();
}
