import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credito } from '../../core/models/credito.model';

@Injectable({
  providedIn: 'root',
})
export class CreditoService {
  private apiUrl = 'http://localhost:8080/api/credito';

  constructor(private http: HttpClient) {}

  getPorNfse(numeroNfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.apiUrl}/${numeroNfse}`);
  }

  getPorNumeroCredito(numeroCredito: string): Observable<Credito> {
    return this.http.get<Credito>(`${this.apiUrl}/credito/${numeroCredito}`);
  }

  criarCredito(credito: Credito): Observable<Credito> {
    return this.http.post<Credito>(this.apiUrl, credito);
  }

  atualizarCredito(
    numeroCredito: string,
    credito: Credito
  ): Observable<Credito> {
    return this.http.put<Credito>(`${this.apiUrl}/${numeroCredito}`, credito);
  }

  deletarCredito(numeroCredito: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${numeroCredito}`);
  }
}
