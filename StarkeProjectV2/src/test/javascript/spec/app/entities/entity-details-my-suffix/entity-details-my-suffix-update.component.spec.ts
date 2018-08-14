/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityDetailsMySuffixUpdateComponent } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix-update.component';
import { EntityDetailsMySuffixService } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix.service';
import { EntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityDetailsMySuffix Management Update Component', () => {
        let comp: EntityDetailsMySuffixUpdateComponent;
        let fixture: ComponentFixture<EntityDetailsMySuffixUpdateComponent>;
        let service: EntityDetailsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityDetailsMySuffixUpdateComponent]
            })
                .overrideTemplate(EntityDetailsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EntityDetailsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityDetailsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityDetailsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EntityDetailsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.entityDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
