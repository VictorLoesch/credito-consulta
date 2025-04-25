import { Component, inject } from '@angular/core';
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
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { Credito } from '../../models/credito.model';
import { CreditoService } from '../../services/credito.service';

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
    MatSnackBarModule,
  ],
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.scss'],
})
export class ConsultaComponent {
  private creditoService = inject(CreditoService);
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  displayedColumns: string[] = [
    'numeroCredito',
    'numeroNfse',
    'valorIssqn',
    'tipoCredito',
    'acoes',
  ];

  resultado: Credito[] = [];

  pesquisado: boolean = false;

  form: FormGroup = this.fb.group({
    numeroCredito: [''],
    numeroNfse: [''],
  });

  buscarCredito(): void {
    this.resultado = [];
    this.pesquisado = false;

    const numeroCredito = this.form.get('numeroCredito')?.value?.trim();
    const numeroNfse = this.form.get('numeroNfse')?.value?.trim();

    if (numeroCredito) {
      this.creditoService.getPorNumeroCredito(numeroCredito).subscribe({
        next: (res) => {
          this.pesquisado = true;
          if (!res || Object.keys(res).length === 0) {
            this.snackBar.open('Crédito não encontrado.', 'Fechar', {
              duration: 4000,
              panelClass: ['snack-warn'],
            });
          } else {
            this.resultado = [res];
          }
        },
        error: (err) => {
          const mensagem = err?.error?.message || 'Erro ao buscar crédito';
          this.snackBar.open(mensagem, 'Fechar', {
            duration: 4000,
            panelClass: ['snack-error'],
          });
          this.pesquisado = true;
        },
      });
    } else if (numeroNfse) {
      // Busca por número de NFS-e (assumindo que o retorno é um array de Credito)
      this.creditoService.getPorNfse(numeroNfse).subscribe({
        next: (res) => {
          this.pesquisado = true;
          if (Array.isArray(res) && res.length === 0) {
            this.snackBar.open('NFS-e não encontrada.', 'Fechar', {
              duration: 4000,
              panelClass: ['snack-warn'],
            });
          } else {
            this.resultado = res;
          }
        },
        error: (err) => {
          const mensagem = err?.error?.message || 'Erro ao buscar crédito';
          this.snackBar.open(mensagem, 'Fechar', {
            duration: 4000,
            panelClass: ['snack-error'],
          });
          this.pesquisado = true;
        },
      });
    } else {
      this.snackBar.open(
        'Preencha o número do crédito ou da NFS-e.',
        'Fechar',
        {
          duration: 4000,
          panelClass: ['snack-warn'],
        }
      );
    }
  }

  // Redireciona para tela de edição
  editar(numeroCredito: string): void {
    this.router.navigate(['/editar', numeroCredito]);
  }

  // Redireciona para tela de cadastro
  criarCredito(): void {
    this.router.navigate(['/cadastro']);
  }

  // Exclui o crédito e atualiza o array de resultados
  excluir(numeroCredito: string): void {
    const confirmacao = confirm('Deseja realmente excluir este crédito?');
    if (confirmacao) {
      this.creditoService.deletarCredito(numeroCredito).subscribe({
        next: () => {
          this.resultado = this.resultado.filter(
            (c) => c.numeroCredito !== numeroCredito
          );
          this.snackBar.open('Crédito excluído com sucesso!', 'Fechar', {
            duration: 4000,
            panelClass: ['snack-success'],
          });
        },
        error: (err) => {
          const mensagem = err?.error?.message || 'Erro ao excluir crédito';
          this.snackBar.open(mensagem, 'Fechar', {
            duration: 4000,
            panelClass: ['snack-error'],
          });
        },
      });
    }
  }
}
