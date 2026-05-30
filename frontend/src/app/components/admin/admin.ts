import { Component, Output, EventEmitter, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html'
})
export class AdminComponent {
  @Output() navigate = new EventEmitter<any>();
  @Output() addWatch = new EventEmitter<any>();

  newWatch = signal({
    name: '',
    brand: '',
    price: 0,
    stock: 0,
    type: 'Mechanical'
  });

  submitForm() {
    if (this.newWatch().name && this.newWatch().brand && this.newWatch().price > 0) {
      this.addWatch.emit({ ...this.newWatch(), id: 'NEW-' + Math.random().toString(36).substr(2, 5) });
      this.newWatch.set({
        name: '',
        brand: '',
        price: 0,
        stock: 0,
        type: 'Mechanical'
      });
    }
  }
}
