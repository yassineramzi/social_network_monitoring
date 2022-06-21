import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

import Societe from '@models/societe.model';
import SocieteService from '@services/societe.service';

@Component({
  selector: 'app-ajout-societes-modal',
  templateUrl: './ajout-societes-modal.component.html'
})
export class AjoutSocietesModalComponent {
  public ajoutSocieteForm: FormGroup = this.formBuilder.group(
    {
      id: new FormControl(null),
      nom: new FormControl(null , Validators.required),
    }
  );

  constructor(
    protected activeModal: NgbActiveModal,
    protected formBuilder: FormBuilder,
    protected toastService: ToastrService,
    protected societeService: SocieteService
  ) {
  }

  public accept(): void {
    let societe: Societe = {
      id: null,
      nom: this.ajoutSocieteForm.value.nom,
    };
    this.societeService.create(societe).subscribe(
      (response: HttpResponse<Societe>) => {
        this.activeModal.close(response.body);
        this.toastService.success('Le sociéte '+societe.nom+' a été créé', 'Création de la sociéte');
      },
      (response: HttpErrorResponse) => {
        this.toastService.error(response.error.error, response.error.message);
      }
    );
  }

  public onClose(): void {
    this.activeModal.close();
  }
}
