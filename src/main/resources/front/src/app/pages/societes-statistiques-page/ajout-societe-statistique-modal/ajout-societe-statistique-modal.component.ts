import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { SocieteStatistiquesService } from '@services/societeStatistique.service';
import SocieteStatistique from '@models/societeStatistique.model';

@Component({
  selector: 'app-ajout-societe-statistique-modal',
  templateUrl: './ajout-societe-statistique-modal.component.html'
})
export class AjoutSocieteStatistiqueModalComponent {

  public ajoutSocieteStatistiqueForm: FormGroup = this.formBuilder.group(
    {
      id: new FormControl(null),
      prixGazoil: new FormControl(null , Validators.required),
      prixEssence: new FormControl(null , Validators.required),
    }
  );

  public idSociete: number;

  constructor(
    protected activeModal: NgbActiveModal,
    protected toastService: ToastrService,
    protected formBuilder: FormBuilder,
    protected societeStatistiqueService: SocieteStatistiquesService
    ) { }

  public accept(): void {
    const societeStatistique: SocieteStatistique = {
      id: null,
      dateStatistique: null,
      prixGazoil: this.ajoutSocieteStatistiqueForm.value.prixGazoil,
      prixEssence: this.ajoutSocieteStatistiqueForm.value.prixEssence,
      societeId: this.idSociete
    };
    this.societeStatistiqueService.create(societeStatistique).subscribe(
      (response: HttpResponse<SocieteStatistique>) => {
        this.activeModal.close(response.body);
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
