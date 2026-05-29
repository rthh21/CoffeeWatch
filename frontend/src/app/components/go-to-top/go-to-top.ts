import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-go-to-top',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './go-to-top.html'
})
export class GoToTopComponent {
  @Input() show: boolean = false;
  @Output() scrollToTop = new EventEmitter<void>();
}
