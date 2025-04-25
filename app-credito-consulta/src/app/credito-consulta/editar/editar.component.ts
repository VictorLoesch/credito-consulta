import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { Credito } from '../../models/credito.model';
import { CreditoService } from '../../services/credito.service';

@Component({
  selector: 'app-editar',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
  ],
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.scss'],
})
export class EditarComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private fb = inject(FormBuilder);
  private service = inject(CreditoService);
  private router = inject(Router);
  private snack = inject(MatSnackBar);

  numeroCredito!: string;

  form: FormGroup = this.fb.group({
    numeroCredito: [{ value: '', disabled: true }, Validators.required],
    numeroNfse: ['', Validators.required],
    dataConstituicao: ['', Validators.required],
    valorIssqn: ['', Validators.required],
    tipoCredito: ['', Validators.required],
    simplesNacional: ['', Validators.required],
    aliquota: ['', Validators.required],
    valorFaturado: ['', Validators.required],
    valorDeducao: ['', Validators.required],
    baseCalculo: ['', Validators.required],
  });

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      const num = params.get('numeroCredito');
      if (num) {
        this.numeroCredito = num;
        this.service.getPorNumeroCredito(num).subscribe({
          next: (c: Credito) => this.form.patchValue(c),
          error: () =>
            this.snack.open('Crédito não encontrado.', 'Fechar', {
              duration: 3000,
              panelClass: ['snack-error'],
            }),
        });
      }
    });
  }

  atualizar() {
    if (this.form.invalid) return;
    const dto = this.form.getRawValue() as Credito;
    this.service.atualizarCredito(this.numeroCredito, dto).subscribe({
      next: () => {
        this.snack.open('Crédito atualizado com sucesso!', 'Fechar', {
          duration: 3000,
          panelClass: ['snack-success'],
        });
        this.router.navigate(['/']);
      },
      error: (err) => {
        const msg = err?.error?.message || 'Erro ao atualizar crédito';
        this.snack.open(msg, 'Fechar', {
          duration: 3000,
          panelClass: ['snack-error'],
        });
      },
    });
  }

  cancelar() {
    this.router.navigate(['/']);
  }
}
