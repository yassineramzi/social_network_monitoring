import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

import { ProfilService } from '@services/profil.service';
import { Profil } from '@models/profil.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-ajout-profil-modal',
  templateUrl: './ajout-profil-modal.component.html'
})
export class AjoutProfilModalComponent implements OnInit {
  public profil: Profil;

  public idDossier: number;

  public ajoutProfilForm: FormGroup = this.formBuilder.group(
    {
      id: new FormControl(null),
      nom: new FormControl('', Validators.required),
      lienYoutube: new FormControl(null),
      lienTwitter: new FormControl(null),
      lienFacebook: new FormControl(null),
      lienInstagram: new FormControl(null)
    }
  );

  constructor(
    protected activeModal: NgbActiveModal,
    protected formBuilder: FormBuilder,
    protected profilService: ProfilService,
    protected toastService: ToastrService
  ) {
  }

  public ngOnInit(): void {
    if (this.profil) {
      this.ajoutProfilForm.patchValue({
        id: this.profil.id,
        nom: this.profil.nom,
        lienYoutube: this.profil.lienYoutube,
        lienTwitter: this.profil.lienTwitter,
        lienFacebook: this.profil.lienFacebook,
        lienInstagram: this.profil.lienInstagram
      });
    }
  }

  public accept(): void {
    let profil: Profil = {
      id: this.ajoutProfilForm.value.id,
      nom: this.ajoutProfilForm.value.nom,
      lienYoutube: this.ajoutProfilForm.value.lienYoutube,
      lienTwitter: this.ajoutProfilForm.value.lienTwitter,
      lienFacebook: this.ajoutProfilForm.value.lienFacebook,
      lienInstagram: this.ajoutProfilForm.value.lienInstagram,
      dossierId: this.idDossier
    };

    if (profil.id) {
      this.profilService.update(profil).subscribe(
        (response: HttpResponse<Profil>) => {
          this.activeModal.close(response.body);
          this.toastService.success('Le profil '+profil.nom+' a été mis à jour', 'Modification du profil');
        },
        (response: HttpErrorResponse) => {
          this.toastService.error(response.error.error, response.error.message);
        }
      );
    } else {
      this.profilService.create(profil).subscribe(
        (response: HttpResponse<Profil>) => {
          this.activeModal.close(response.body);
          this.toastService.success('Le profil '+profil.nom+' a été créé', 'Création du profil');
        },
        (response: HttpErrorResponse) => {
          this.toastService.error(response.error.error, response.error.message);
        }
      );
    }
  }

  public onClose(): void {
    this.activeModal.close();
  }

}
