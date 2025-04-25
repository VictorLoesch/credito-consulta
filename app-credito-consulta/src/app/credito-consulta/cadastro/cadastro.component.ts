import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CreditoService } from '../../services/credito.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    MatNativeDateModule,
    MatDatepickerModule,
  ],
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss'],
})
export class CadastroComponent {
  private fb = inject(FormBuilder);
  private service = inject(CreditoService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  form: FormGroup = this.fb.group({
    numeroCredito: ['', Validators.required],
    numeroNfse: ['', Validators.required],
    dataConstituicao: [null, Validators.required],
    valorIssqn: ['', Validators.required],
    tipoCredito: ['', Validators.required],
    simplesNacional: ['', Validators.required],
    aliquota: ['', Validators.required],
    valorFaturado: ['', Validators.required],
    valorDeducao: ['', Validators.required],
    baseCalculo: ['', Validators.required],
  });

  salvar() {
    if (this.form.invalid) return;

    this.service.criarCredito(this.form.value).subscribe({
      next: () => {
        this.snackBar.open('Crédito criado com sucesso!', 'Fechar', {
          duration: 3000,
        });
        this.router.navigate(['/']);
      },
      error: () => {
        this.snackBar.open('Erro ao criar crédito!', 'Fechar', {
          duration: 3000,
        });
      },
    });
  }

  voltar() {
    this.router.navigate(['/']);
  }
  mostrarMensagem(erro: string) {
    this.snackBar.open(erro, 'Fechar', {
      duration: 5000,
      verticalPosition: 'top',
      panelClass: ['snackbar-erro'],
    });
  }
}
