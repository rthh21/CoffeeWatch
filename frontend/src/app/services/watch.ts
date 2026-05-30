import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WatchService {
  private apiUrl = '/api/watches';

  constructor(private http: HttpClient) { }

  getAllWatches(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getWatchesByBrand(brand: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/brand/${brand}`);
  }

  placeOrder(order: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/order`, order);
  }
}
