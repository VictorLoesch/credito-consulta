import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { Credito } from '../models/credito.model';
import { CreditoService } from '../services/credito.service';

@Component({
  selector: 'app-consulta',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
  ],
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.scss'],
})
export class ConsultaComponent {
  private creditoService = inject(CreditoService);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private dialog = inject(MatDialog);

  displayedColumns: string[] = [
    'numeroCredito',
    'numeroNfse',
    'valorIssqn',
    'tipoCredito',
    'acoes',
  ];

  resultado = signal<Credito[]>([]);
  erroMensagem = signal<string | null>(null);

  form: FormGroup = this.fb.group({
    numeroCredito: [''],
    numeroNfse: [''],
  });

  buscar() {
    const { numeroCredito, numeroNfse } = this.form.value;
    this.erroMensagem.set(null);

    if (numeroCredito) {
      this.creditoService.getPorNumeroCredito(numeroCredito).subscribe({
        next: (res) => this.resultado.set([res]),
        error: (err) =>
          this.erroMensagem.set(
            err.error?.mensagem || 'Erro ao buscar crédito'
          ),
      });
    } else if (numeroNfse) {
      this.creditoService.getPorNfse(numeroNfse).subscribe({
        next: (res) => this.resultado.set(res),
        error: (err) =>
          this.erroMensagem.set(err.error?.mensagem || 'Erro ao buscar NFS-e'),
      });
    }
  }

  editar(numeroCredito: string) {
    this.router.navigate(['/editar', numeroCredito]);
  }

  excluir(numeroCredito: string) {
    const confirmacao = confirm('Deseja realmente excluir este crédito?');
    if (confirmacao) {
      this.creditoService.deletarCredito(numeroCredito).subscribe(() => {
        const novaLista = this.resultado().filter(
          (c) => c.numeroCredito !== numeroCredito
        );
        this.resultado.set(novaLista);
      });
    }
  }

  criarCredito() {
    this.router.navigate(['/cadastro']);
  }
}
